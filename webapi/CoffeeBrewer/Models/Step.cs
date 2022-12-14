using CoffeeBrewer.Contracts.CoffeeBrewer.Step;
using CoffeeBrewer.Contracts.Util;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Models;

public class Step
{
    public const int MinTitleLenght = 3;
    public const int MaxTitleLenght = 50;
    public const int MinDescriptionLenght = 10;
    public const int MaxDescriptionLenght = 5000;
    public const int MinDuration = 0;
    public const int MaxDuration = 7200;
    public const int MinWaterAmount = 1;
    public const int MaxWaterAmount = 100000;
    
    public int Id { get; set; }
    public int Order { get; set; }
    public StepType StepType { get; set; }
    public string Title { get; set; }
    public string? Description { get; set; }
    public int DurationInSeconds { get; set; }
    public int RecipeId { get; set; }
    public Recipe Recipe { get; set; }
    public double? WaterAmount { get; set; }

    public static ErrorOr<Step> Create(int order, StepType stepType, string title, string? description, int durationInSeconds, double? waterAmount, Recipe? recipe = null, int? id = null)
    {
        List<Error> errors = new();
        if(order < 0) 
        {
            errors.Add(Errors.Step.InvalidOrder);
        }
        if(title.Length is < MinTitleLenght or > MaxTitleLenght) 
        {
            errors.Add(Errors.Step.InvalidTitle);
        }
        if(description != null && description.Length is < MinDescriptionLenght or > MaxDescriptionLenght) 
        {
            errors.Add(Errors.Step.InvalidDescription);
        }
        if(durationInSeconds is < MinDuration or > MaxDuration) 
        {
            errors.Add(Errors.Step.InvalidDuration);
        }
        if(stepType.Equals(StepType.Pour) && waterAmount is < MinWaterAmount or > MaxWaterAmount) 
        {
            errors.Add(Errors.Step.InvalidWaterAmount);
        }
        if(errors.Count > 0)
        {
            return errors;
        }
        return id != null ? 
            new Step { 
                Id = (int)id,
                Order = order,
                StepType = stepType,
                Title = title,
                Description = description,
                DurationInSeconds = durationInSeconds,
                WaterAmount = waterAmount 
            } :
            new Step { Order = order,
                StepType = stepType,
                Title = title,
                Description = description,
                DurationInSeconds = durationInSeconds,
                Recipe = recipe!,
                WaterAmount = waterAmount
            };
    }

    internal static ErrorOr<Step> From(CreateStepRequest request, Recipe recipe)
    {
        return Create(request.Order, (StepType)request.StepType, request.Title, request.Description, request.DurationInSeconds, request.WaterAmount, recipe);
    }

    internal static ErrorOr<Step> From(int id, UpsertStepRequest request)
    {
        return Create(request.Order, (StepType)request.StepType, request.Title, request.Description, request.DurationInSeconds, request.WaterAmount, id: id);
    }
}