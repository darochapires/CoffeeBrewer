using CoffeeBrewer.Models;
using ErrorOr;

namespace CoffeeBrewer.Services.Methods;

public interface IMethodService
{
    ErrorOr<Created> CreateMethod(Method method);
    ErrorOr<Method> GetMethod(Guid id);
    ErrorOr<UpsertedMethod>  UpsertMethod(Method method);
    ErrorOr<Deleted> DeleteMethod(Guid id);
}