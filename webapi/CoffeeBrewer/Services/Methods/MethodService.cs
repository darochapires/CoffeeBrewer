using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Methods
{
    public class MethodService : IMethodService
    {
        private static readonly Dictionary<Guid, Method> _methods = new();

        public ErrorOr<Created> CreateMethod(Method method)
        {
            _methods.Add(method.Id, method);
            return Result.Created;
        }

        public ErrorOr<Method> GetMethod(Guid id)
        {
            if(_methods.TryGetValue(id, out var method))
            {
                return method;
            }
            return Errors.Method.NotFound;
        }

        public ErrorOr<UpsertedMethod> UpsertMethod(Method method)
        {
            var IsNewlyCreated = !_methods.ContainsKey(method.Id);
            _methods[method.Id] = method;
            return new UpsertedMethod(IsNewlyCreated);
        }

        public ErrorOr<Deleted> DeleteMethod(Guid id)
        {
            _methods.Remove(id);
            return Result.Deleted;
        }
    }
}