using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Models;

public class Step
{
    public const int MinTitleLenght = 3;
    public const int MaxTitleLenght = 50;
    public const int MinDescriptionLenght = 10;
    public const int MaxDescriptionLenght = 5000;
    
    public int Id { get; set; }
    public int Order { get; set; }
    public StepType StepType;
    public string Title { get; set; }
    public string? Description { get; set; }
    public int DurationInSeconds { get; set; }
    public Recipe Recipe { get; set; }
    public double? WaterAmount { get; set; }

    // protected Step(int id, int order, StepType stepType, string title, string description, int durationInSeconds, Recipe recipe, double? waterAmount = null) 
    // {
    //     Id = id;
    //     Order = order;
    //     Title = title;
    //     Description = description;
    //     DurationInSeconds = durationInSeconds;
    //     Recipe = recipe;
    //     StepType = stepType;
    //     WaterAmount = waterAmount;
    // }
/*
    public static ErrorOr<Step> Create(string title, string description, GrindSize grindSize, Recipe recipe, Guid? id = null)
    {
        List<Error> errors = new();
        if(title.Length is < MinTitleLenght or > MaxTitleLenght) 
        {
            errors.Add(Errors.Step.InavalidTitle);
        }
        if(description.Length is < MinDescriptionLenght or > MaxDescriptionLenght) 
        {
            errors.Add(Errors.Step.InavalidDescription);
        }
        if(errors.Count > 0)
        {
            return errors;
        }
        return new Step(id ?? Guid.NewGuid(), title, description, grindSize, DateTime.UtcNow, recipe);
    }

    internal static ErrorOr<Step> From(CreateStepRequest request, Recipe recipe)
    {
        return Create(request.Title, request.Description, recipe);
    }

    internal static ErrorOr<Step> From(Guid id, UpsertStepRequest request, Recipe recipe)
    {
        return Create(request.Title, request.Description, recipe, id);
    }*/
}