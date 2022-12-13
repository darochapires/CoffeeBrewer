using CoffeeBrewer.Contracts.Util;

namespace CoffeeBrewer.Contracts.CoffeeBrewer.Recipe;

public record RecipeResponse(
    int Id,
    string Name,
    string? Description,
    double WaterTemperature,
    GrindSize GrindSize,
    int MethodId
    //TODO: ICollection<Step>? Steps
);