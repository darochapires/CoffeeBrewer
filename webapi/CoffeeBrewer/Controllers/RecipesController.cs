using CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;
using CoffeeBrewer.Contracts.Util;
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
            return Problem(getMethodResult.Errors);
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

    [HttpGet("{id}")]
    public IActionResult GetRecipe(int id)
    {
        var getRecipeResult = _recipeService.GetRecipe(id);

        return getRecipeResult.Match(
            recipe => Ok(MapRecipeResponse(recipe)),
            errors => Problem(errors)
        ); 
    }

    [HttpGet("GetByMethod/{methodId}")]
    public IActionResult GetRecipesByMethod(int methodId)
    {
        var getRecipeResult = _recipeService.GetRecipesByMethod(methodId);

        return getRecipeResult.Match(
            recipes => Ok(MapRecipeResponse(recipes)),
            errors => Problem(errors)
        ); 
    }

    [HttpPut("{id}")]
    public IActionResult UpsertRecipe(int id, UpsertRecipeRequest request)
    {
        var getMethodResult = _methodService.GetMethod(request.MethodId);
        if(getMethodResult.IsError) 
        {
            return Problem(getMethodResult.Errors);
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
            upserted => NoContent(),
            errors => Problem(errors)
        );
    }

    [HttpDelete("{id}")]
    public IActionResult DeleteRecipe(int id)
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

    private static List<RecipeResponse> MapRecipeResponse(List<Recipe> recipes)
    {
        var recipesResponse = new List<RecipeResponse>();
        foreach (var recipe in recipes)
        {
            recipesResponse.Add(MapRecipeResponse(recipe));
        }
        return recipesResponse;
    }

    private static RecipeResponse MapRecipeResponse(Recipe recipe)
    {
        var waterAmount = recipe.Steps?.Where(s => s.StepType.Equals(StepType.Pour)).Sum(s => s.WaterAmount);
        return new RecipeResponse(
                recipe.Id,
                recipe.Name,
                recipe.Description,
                recipe.CoffeeAmount,
                recipe.WaterTemperature,
                recipe.GrindSize,
                recipe.MethodId,
                waterAmount ?? 0
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