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
}