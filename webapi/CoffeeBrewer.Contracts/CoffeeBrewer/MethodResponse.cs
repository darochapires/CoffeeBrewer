namespace CoffeeBrewer.Contracts.CoffeeBrewer;

public record MethodResponse(
    Guid Id,
    string Name,
    string Description,
    DateTime LastModifiedDateTime
);