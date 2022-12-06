using CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;
using CoffeeBrewer.Models;
using CoffeeBrewer.Services.Methods;
using CoffeeBrewer.Services.Recipes;
using Microsoft.AspNetCore.Mvc;

namespace CoffeeBrewer.Controllers;

public class RecipesController : ApiController
{
    private readonly IRecipeService _recipeService;
    private readonly IMethodService _methodService;

    public RecipesController(IRecipeService recipeService, IMethodService methodService)
    {
        _recipeService = recipeService;
        _methodService = methodService;
    }

    [HttpPost]
    public IActionResult CreateRecipe(CreateRecipeRequest request)
    {
        var getMethodResult = _methodService.GetMethod(request.MethodId);
        if(getMethodResult.IsError) 
        {
            //TODO: add to errors
        }
        
        var method = getMethodResult.Value;
        var requestToRecipeResult = Recipe.From(
            request, 
            method
        );

        if(requestToRecipeResult.IsError)
        {
            return Problem(requestToRecipeResult.Errors);
        }

        var recipe = requestToRecipeResult.Value;
        var createRecipeResult = _recipeService.CreateRecipe(recipe);

        return createRecipeResult.Match(
            created => CreatedAtGetRecipe(recipe),
            errors => Problem(errors)
        );
    }

    [HttpGet("{id:guid}")]
    public IActionResult GetRecipe(Guid id)
    {
        var getRecipeResult = _recipeService.GetRecipe(id);

        return getRecipeResult.Match(
            recipe => Ok(MapRecipeResponse(recipe)),
            errors => Problem(errors)
        ); 
    }

    [HttpPut("{id:guid}")]
    public IActionResult UpsertRecipe(Guid id, UpsertRecipeRequest request)
    {
        var getMethodResult = _methodService.GetMethod(request.MethodId);
        if(getMethodResult.IsError) 
        {
            //TODO: add to errors
        }
        
        var method = getMethodResult.Value;
        var requestToRecipeResult = Recipe.From(
            id,
            request,
            method
        );

        if(requestToRecipeResult.IsError)
        {
            return Problem(requestToRecipeResult.Errors);
        }
        
        var recipe = requestToRecipeResult.Value;
        var upsertRecipeResult = _recipeService.UpsertRecipe(recipe);

        return upsertRecipeResult.Match(
            upserted => upserted.IsNewlyCreated ? CreatedAtGetRecipe(recipe) : NoContent(),
            errors => Problem(errors)
        );
    }

    [HttpDelete("{id:guid}")]
    public IActionResult DeleteRecipe(Guid id)
    {
        var deleteRecipeResult = _recipeService.DeleteRecipe(id);
        if(deleteRecipeResult.IsError) {
            return Problem(deleteRecipeResult.Errors);
        }
        return deleteRecipeResult.Match(
            deleted => NoContent(),
            errors => Problem(errors)
        );
    }

    private static RecipeResponse MapRecipeResponse(Recipe recipe)
    {
        return new RecipeResponse(
            recipe.Id,
            recipe.Name,
            recipe.Description,
            recipe.LastModifiedDateTime
        );
    }

    private IActionResult CreatedAtGetRecipe(Recipe recipe)
    {
        return CreatedAtAction(
            actionName: nameof(GetRecipe),
            routeValues: new { id = recipe.Id },
            value: MapRecipeResponse(recipe)
        );
    }
}