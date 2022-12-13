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

        public static Error InvalidName => Error.Validation(
            code: "Method.InvalidName",
            description: $"Method Name must have between {Models.Method.MinNameLenght} and  {Models.Method.MaxNameLenght} characters."
        );

        public static Error InvalidDescription => Error.Validation(
            code: "Method.InvalidDescription",
            description: $"Method Description must have between {Models.Method.MinDescriptionLenght} and  {Models.Method.MaxDescriptionLenght} characters."
        );

        public static Error UnexpectedError => Error.Unexpected(
            code: "Method.UnexpectedError",
            description: $"Something went wrong while updating data. Please try again later."
        );

    }
    
    public static class Recipe
    {
        public static Error NotFound => Error.NotFound(
            code: "Recipe.NotFound",
            description: "Recipe was not found"
        );

        public static Error InvalidName => Error.Validation(
            code: "Recipe.InvalidName",
            description: $"Recipe Name must have between {Models.Recipe.MinNameLenght} and {Models.Recipe.MaxNameLenght} characters."
        );

        public static Error InvalidDescription => Error.Validation(
            code: "Recipe.InvalidDescription",
            description: $"Recipe Description must have between {Models.Recipe.MinDescriptionLenght} and {Models.Recipe.MaxDescriptionLenght} characters."
        );

        public static Error InvalidCoffeeAmount => Error.Validation(
            code: "Recipe.InvalidCoffeeAmount",
            description: $"Coffee Amount must be between {Models.Recipe.MinCoffeeAmount} and {Models.Recipe.MaxCoffeeAmount}."
        );

        public static Error InvalidWaterTemperature => Error.Validation(
            code: "Recipe.InvalidWaterTemperature",
            description: $"Water Temperature must be between {Models.Recipe.MinWaterTemperature} and {Models.Recipe.MaxWaterTemperature}."
        );

        public static Error UnexpectedError => Error.Unexpected(
            code: "Recipe.UnexpectedError",
            description: $"Something went wrong while updating data. Please try again later."
        );

    }
    
    public static class Step
    {
        public static Error NotFound => Error.NotFound(
            code: "Step.NotFound",
            description: "Step was not found"
        );

        public static Error InvalidOrder => Error.Validation(
            code: "Step.InvalidOrder",
            description: $"Step Order must not be negative."
        );

        public static Error InvalidTitle => Error.Validation(
            code: "Step.InvalidTitle",
            description: $"Step Title must have between {Models.Step.MinTitleLenght} and {Models.Step.MaxTitleLenght} characters."
        );

        public static Error InvalidDescription => Error.Validation(
            code: "Step.InvalidDescription",
            description: $"Step Description must have between {Models.Step.MinDescriptionLenght} and {Models.Step.MaxDescriptionLenght} characters."
        );

        public static Error InvalidDuration => Error.Validation(
            code: "Step.InvalidDuration",
            description: $"Step Duration must be between {Models.Step.MinDuration} and {Models.Step.MaxDuration} seconds."
        );

        public static Error InvalidWaterAmount => Error.Validation(
            code: "Step.InvalidWaterAmount",
            description: $"Water Amount must be between {Models.Step.MinWaterAmount} and {Models.Step.MaxWaterAmount} milliliters."
        );

        public static Error UnexpectedError => Error.Unexpected(
            code: "Step.UnexpectedError",
            description: $"Something went wrong while updating data. Please try again later."
        );

    }
}