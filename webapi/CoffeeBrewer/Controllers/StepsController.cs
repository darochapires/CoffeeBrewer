using CoffeeBrewer.Contracts.CoffeeBrewer.Step;
using CoffeeBrewer.Models;
using CoffeeBrewer.Services.Recipes;
using CoffeeBrewer.Services.Steps;
using Microsoft.AspNetCore.Mvc;

namespace CoffeeBrewer.Controllers;

public class StepsController : ApiController
{
    private readonly IStepService _stepService;
    private readonly IRecipeService _recipeService;

    public StepsController(IStepService stepService, IRecipeService recipeService)
    {
        _stepService = stepService;
        _recipeService = recipeService;
    }

    [HttpPost]
    public IActionResult CreateStep(CreateStepRequest request)
    {
        var getRecipeResult = _recipeService.GetRecipe(request.RecipeId);
        if(getRecipeResult.IsError) 
        {
            return Problem(getRecipeResult.Errors);
        }
        
        var recipe = getRecipeResult.Value;
        var requestToStepResult = Step.From(
            request, 
            recipe
        );

        if(requestToStepResult.IsError)
        {
            return Problem(requestToStepResult.Errors);
        }

        var step = requestToStepResult.Value;
        var createStepResult = _stepService.CreateStep(step);

        return createStepResult.Match(
            created => CreatedAtGetStep(step),
            errors => Problem(errors)
        );
    }

    [HttpGet("{id}")]
    public IActionResult GetStep(int id)
    {
        var getStepResult = _stepService.GetStep(id);

        return getStepResult.Match(
            step => Ok(MapStepResponse(step)),
            errors => Problem(errors)
        ); 
    }

    [HttpGet("{id}")]
    public IActionResult GetStepsByRecipe(int recipeId)
    {
        var getStepResult = _stepService.GetStepsByRecipe(recipeId);

        return getStepResult.Match(
            steps => Ok(MapStepResponse(steps)),
            errors => Problem(errors)
        ); 
    }

    [HttpPut("{id}")]
    public IActionResult UpsertStep(int id, UpsertStepRequest request)
    {
        var getRecipeResult = _recipeService.GetRecipe(request.RecipeId);
        if(getRecipeResult.IsError) 
        {
            return Problem(getRecipeResult.Errors);
        }
        
        var recipe = getRecipeResult.Value;
        var requestToStepResult = Step.From(
            id,
            request,
            recipe
        );

        if(requestToStepResult.IsError)
        {
            return Problem(requestToStepResult.Errors);
        }
        
        var step = requestToStepResult.Value;
        var upsertStepResult = _stepService.UpsertStep(step);

        return upsertStepResult.Match(
            upserted => NoContent(),
            errors => Problem(errors)
        );
    }

    [HttpDelete("{id}")]
    public IActionResult DeleteStep(int id)
    {
        var deleteStepResult = _stepService.DeleteStep(id);
        if(deleteStepResult.IsError) {
            return Problem(deleteStepResult.Errors);
        }
        return deleteStepResult.Match(
            deleted => NoContent(),
            errors => Problem(errors)
        );
    }
    
    private static List<StepResponse> MapStepResponse(List<Step> steps)
    {
        var stepsResponse = new List<StepResponse>();
        foreach (var step in steps)
        {
            stepsResponse.Add(MapStepResponse(step));
        }
        return stepsResponse;
    }

    private static StepResponse MapStepResponse(Step step)
    {
        return new StepResponse(
                step.Id,
                step.Order,
                step.StepType,
                step.Title,
                step.Description,
                step.DurationInSeconds,
                step.Recipe.Id,
                step.WaterAmount);
    }

    private IActionResult CreatedAtGetStep(Step step)
    {
        return CreatedAtAction(
            actionName: nameof(GetStep),
            routeValues: new { id = step.Id },
            value: MapStepResponse(step)
        );
    }
}