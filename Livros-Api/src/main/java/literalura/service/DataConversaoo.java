package literalura.service;

public interface DataConversaoo {
    <T> T getData(String json, Class<T> tClass);
}
