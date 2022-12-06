using CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;
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
    public DateTime LastModifiedDateTime { get; }
    public Method Method { get; }

    private Recipe(Guid id, string name, string descriptioni, DateTime lastModifiedDateTime, Method method) 
    {
        Id = id;
        Name = name;
        Description = descriptioni;
        LastModifiedDateTime = lastModifiedDateTime;
        Method = method;
    }

    public static ErrorOr<Recipe> Create(string name, string description, Method method, Guid? id = null)
    {
        List<Error> errors = new();
        if(name.Length is < MinNameLenght or > MaxNameLenght) 
        {
            errors.Add(Errors.Method.InavalidName);
        }
        if(description.Length is < MinDescriptionLenght or > MaxDescriptionLenght) 
        {
            errors.Add(Errors.Method.InavalidDescription);
        }
        if(errors.Count > 0)
        {
            return errors;
        }
        return new Recipe(id ?? Guid.NewGuid(), name, description, DateTime.UtcNow, method);
    }

    internal static ErrorOr<Recipe> From(CreateRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, method);
    }

    internal static ErrorOr<Recipe> From(Guid id, UpsertRecipeRequest request, Method method)
    {
        return Create(request.Name, request.Description, method, id);
    }
}