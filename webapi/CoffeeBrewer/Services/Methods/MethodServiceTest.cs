using CoffeeBrewer.Models;
using CoffeeBrewer.ServiceErrors;
using ErrorOr;

namespace CoffeeBrewer.Services.Methods
{
    public class MethodServiceTest : IMethodService
    {
        private static readonly Dictionary<int, Method> _methods = new();

        public ErrorOr<Created> CreateMethod(Method method)
        {
            _methods.Add(method.Id, method);
            return Result.Created;
        }

        public ErrorOr<Method> GetMethod(int id)
        {
            if(_methods.TryGetValue(id, out var method))
            {
                return method;
            }
            return Errors.Method.NotFound;
        }

        public ErrorOr<Updated> UpsertMethod(Method method)
        {
            var IsNewlyCreated = !_methods.ContainsKey(method.Id);
            _methods[method.Id] = method;
            return Result.Updated;
        }

        public ErrorOr<Deleted> DeleteMethod(int id)
        {
            _methods.Remove(id);
            return Result.Deleted;
        }
    }
}