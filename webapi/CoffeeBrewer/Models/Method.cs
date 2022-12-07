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

    public Guid Id { get; }
    public string Name { get; }
    public string Description { get; }
    public DateTime LastModifiedDateTime { get; }
    public ICollection<Recipe>? Recipes { get; }

    private Method(Guid id, string name, string description, DateTime lastModifiedDateTime) 
    {
        Id = id;
        Name = name;
        Description = description;
        LastModifiedDateTime = lastModifiedDateTime;
    }

    public static ErrorOr<Method> Create(string name, string description, Guid? id = null) 
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
        return new Method(id ?? Guid.NewGuid(), name, description, DateTime.UtcNow);
    }

    internal static ErrorOr<Method> From(CreateMethodRequest request)
    {
        return Create(request.Name, request.Description);
    }

    internal static ErrorOr<Method> From(Guid id, UpsertMethodRequest request)
    {
        return Create(request.Name, request.Description, id);
    }
}