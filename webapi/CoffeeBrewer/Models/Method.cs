using CoffeeBrewer.Contracts.CoffeeBrewer.Method;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Models;

public class Method
{
    public const int MinNameLenght = 3;
    public const int MaxNameLenght = 50;
    public const int MinDescriptionLenght = 10;
    public const int MaxDescriptionLenght = 5000;

    public int Id { get; set; }
    public string Name { get; set; }
    public string? Description { get; set; }
    public ICollection<Recipe>? Recipes { get; set; }

    // private Method(int id, string name, string? description, ICollection<Recipe>? recipes) 
    // {
    //     Id = id;
    //     Name = name;
    //     Description = description;
    //     Recipes = recipes;
    // }

    private static ErrorOr<Method> Create(string name, string description, int? id = null) 
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

        return id != null ? 
            new Method { Id = (int)id, Name = name, Description = description, Recipes = null } : 
            new Method { Name = name, Description = description, Recipes = null };
    }

    internal static ErrorOr<Method> From(CreateMethodRequest request)
    {
        return Create(request.Name, request.Description);
    }

    internal static ErrorOr<Method> From(int id, UpsertMethodRequest request)
    {
        return Create(request.Name, request.Description, id);
    }
}