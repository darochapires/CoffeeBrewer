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
    
    public int Id { get; set; }
    public string Name { get; set; }
    public string? Description { get; set; }
   /* public double WaterAmount { get; set; }
    public double CoffeeAmount { get; set; }
    public double WaterTemperature { get; set; }*/
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

    public static ErrorOr<Recipe> Create(string name, string description, GrindSize grindSize, Method method, int? id = null)
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
        return new Recipe { Id = id ?? -1, Name = name, Description = description, GrindSize = grindSize, Method = method, Steps = null};
    }

    internal static ErrorOr<Recipe> From(CreateRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, request.GrindSize, method);
    }

    internal static ErrorOr<Recipe> From(int id, UpsertRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, request.GrindSize, method, id);
    }
}