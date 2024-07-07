package literalura.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class DataConversao implements DataConversaoo {
    private ObjectMapper mapper = new ObjectMapper();

    public <T> T getData(String json, Class<T> tClass) {
        try {
            return mapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> getList(String json, Class<T> tClass) {
        CollectionType list = mapper.getTypeFactory()
                .constructCollectionType(List.class, tClass);
        try {
            return mapper.readValue(json, list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractObjectFromJson(String json, String object) {
        try {
            JsonNode completeJson = mapper.readTree(json);
            JsonNode jsonLivros = completeJson.path("resultado");
            return jsonLivros.toString();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
