using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Models;

public class Step
{
    public const int MinTitleLenght = 3;
    public const int MaxTitleLenght = 50;
    public const int MinDescriptionLenght = 10;
    public const int MaxDescriptionLenght = 5000;
    
    public Guid Id { get; }
    public int Order { get; }
    public StepType StepType;
    public string Title { get; }
    public string Description { get; }
    public int DurationInSeconds { get; }
    public DateTime LastModifiedDateTime { get; }
    public Recipe Recipe { get; }
    public double? WaterAmount { get; }

    protected Step(Guid id, int order, StepType stepType, string title, string description, int durationInSeconds, DateTime lastModifiedDateTime, Recipe recipe, double? waterAmount = null) 
    {
        Id = id;
        Order = order;
        Title = title;
        Description = description;
        DurationInSeconds = durationInSeconds;
        LastModifiedDateTime = lastModifiedDateTime;
        Recipe = recipe;
        StepType = stepType;
        LastModifiedDateTime = lastModifiedDateTime;
        WaterAmount = waterAmount;
    }
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