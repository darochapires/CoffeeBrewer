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
    public const int MinWaterTemperature = 0;
    public const int MaxWaterTemperature = 120;
    
    public int Id { get; set; }
    public string Name { get; set; }
    public string? Description { get; set; }
   /* public double WaterAmount { get; set; }
    public double CoffeeAmount { get; set; }*/
    public double WaterTemperature { get; set; }
    public GrindSize GrindSize { get; set; }
    public Method Method { get; set; }
    public ICollection<Step>? Steps { get; set; }

    // private Recipe(int id, string name, string? description/*, double waterAmount, double coffeeAmount, double waterTemperature*/, GrindSize grindSize, Method method, ICollection<Step>? steps) 
    // {
    //     Id = id;
    //     Name = name;
    //     Description = description;
    //     /*WaterAmount = waterAmount;
    //     CoffeeAmount = coffeeAmount;
    //     WaterTemperature = waterTemperature;*/
    //     GrindSize = grindSize;
    //     Method = method;
    //     Steps = steps;
    // }

    public static ErrorOr<Recipe> Create(string name, string? description, double waterTemperature, GrindSize grindSize, Method method, List<Step> steps, int? id = null)
    {
        List<Error> errors = new();
        if(name.Length is < MinNameLenght or > MaxNameLenght) 
        {
            errors.Add(Errors.Recipe.InvalidName);
        }
        if(description != null && description.Length is < MinDescriptionLenght or > MaxDescriptionLenght) 
        {
            errors.Add(Errors.Recipe.InvalidDescription);
        }
        if(waterTemperature is < MinWaterTemperature or > MaxWaterTemperature) 
        {
            errors.Add(Errors.Recipe.InvalidWaterTemperature);
        }
        if(errors.Count > 0)
        {
            return errors;
        }

        return id != null ? 
            new Recipe { Id = (int)id, Name = name, Description = description, WaterTemperature = waterTemperature, GrindSize = grindSize, Method = method, Steps = steps } :
            new Recipe { Name = name, Description = description, WaterTemperature = waterTemperature, GrindSize = grindSize, Method = method, Steps = steps };
    }

    internal static ErrorOr<Recipe> From(CreateRecipeRequest request, Method method)
    {
        var steps = new List<Step>();
        var recipe = Create(request.Name, request.Description, request.WaterTemperature, request.GrindSize, method, null);
        if(!recipe.IsError)
        {
            foreach (var step in request.Steps)
            {
                var result = Step.From(request.Steps[0], recipe.Value);
                if(result.IsError) {
                    return result.Errors;
                }
                steps.Add(result.Value);
            }
            recipe.Value.Steps = steps;
        }
        return recipe;
    }

    internal static ErrorOr<Recipe> From(int id, UpsertRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, request.WaterTemperature, request.GrindSize, method, null, id);
    }
}