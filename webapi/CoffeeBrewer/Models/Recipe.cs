using CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;
using CoffeeBrewer.Contracts.Util;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Models;

public class Recipe
{
    public const int MinNameLenght = 3;
    public const int MaxNameLenght = 50;
    public const int MinDescriptionLenght = 10;
    public const int MaxDescriptionLenght = 5000;
    
    public Guid Id { get; }
    public string Name { get; }
    public string Description { get; }
   /* public double WaterAmount { get; }
    public double CoffeeAmount { get; }
    public double WaterTemperature { get; }*/
    public GrindSize GrindSize { get; }
    public DateTime LastModifiedDateTime { get; }
    public Method Method { get; }
    public ICollection<Step>? Steps { get; }

    private Recipe(Guid id, string name, string description/*, double waterAmount, double coffeeAmount, double waterTemperature*/, GrindSize grindSize, DateTime lastModifiedDateTime, Method method) 
    {
        Id = id;
        Name = name;
        Description = description;
        /*WaterAmount = waterAmount;
        CoffeeAmount = coffeeAmount;
        WaterTemperature = waterTemperature;*/
        GrindSize = grindSize;
        LastModifiedDateTime = lastModifiedDateTime;
        Method = method;
    }

    public static ErrorOr<Recipe> Create(string name, string description, GrindSize grindSize, Method method, Guid? id = null)
    {
        List<Error> errors = new();
        if(name.Length is < MinNameLenght or > MaxNameLenght) 
        {
            errors.Add(Errors.Recipe.InavalidName);
        }
        if(description.Length is < MinDescriptionLenght or > MaxDescriptionLenght) 
        {
            errors.Add(Errors.Recipe.InavalidDescription);
        }
        if(errors.Count > 0)
        {
            return errors;
        }
        return new Recipe(id ?? Guid.NewGuid(), name, description, grindSize, DateTime.UtcNow, method);
    }

    internal static ErrorOr<Recipe> From(CreateRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, request.GrindSize, method);
    }

    internal static ErrorOr<Recipe> From(Guid id, UpsertRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, request.GrindSize, method, id);
    }
}