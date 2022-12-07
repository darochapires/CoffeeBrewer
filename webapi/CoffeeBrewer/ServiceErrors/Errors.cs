using ErrorOr;

namespace CoffeeBrewer.ServiceErrors;

public static class Errors
{
    public static class Method
    {
        public static Error NotFound => Error.NotFound(
            code: "Method.NotFound",
            description: "Method was not found"
        );

        public static Error InavalidName => Error.Validation(
            code: "Method.InvalidName",
            description: $"Method name must have between {Models.Method.MinNameLenght} and  {Models.Method.MaxNameLenght} characters."
        );

        public static Error InavalidDescription => Error.Validation(
            code: "Method.InavalidDescription",
            description: $"Method description must have between {Models.Method.MinDescriptionLenght} and  {Models.Method.MaxDescriptionLenght} characters."
        );
    }
    
    public static class Recipe
    {
        public static Error NotFound => Error.NotFound(
            code: "Recipe.NotFound",
            description: "Recipe was not found"
        );

        public static Error InavalidName => Error.Validation(
            code: "Recipe.InvalidName",
            description: $"Recipe name must have between {Models.Recipe.MinNameLenght} and  {Models.Recipe.MaxNameLenght} characters."
        );

        public static Error InavalidDescription => Error.Validation(
            code: "Recipe.InavalidDescription",
            description: $"Recipe description must have between {Models.Recipe.MinDescriptionLenght} and  {Models.Recipe.MaxDescriptionLenght} characters."
        );

    }
    
    public static class Step
    {
        public static Error NotFound => Error.NotFound(
            code: "Step.NotFound",
            description: "Step was not found"
        );

        public static Error InavalidTitle => Error.Validation(
            code: "Step.InvalidTitle",
            description: $"Step title must have between {Models.Step.MinTitleLenght} and  {Models.Step.MaxTitleLenght} characters."
        );

        public static Error InavalidDescription => Error.Validation(
            code: "Step.InavalidDescription",
            description: $"Step description must have between {Models.Step.MinDescriptionLenght} and  {Models.Step.MaxDescriptionLenght} characters."
        );

    }
}