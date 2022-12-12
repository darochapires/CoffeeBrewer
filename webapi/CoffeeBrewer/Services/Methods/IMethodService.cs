using CoffeeBrewer.Models;
using ErrorOr;

namespace CoffeeBrewer.Services.Methods;

public interface IMethodService
{
    ErrorOr<Created> CreateMethod(Method method);
    ErrorOr<Method> GetMethod(int id);
    ErrorOr<Updated> UpsertMethod(Method method);
    ErrorOr<Deleted> DeleteMethod(int id);
}