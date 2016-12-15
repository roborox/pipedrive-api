package ru.roborox.api.pipedrive.serialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.IntNode;

abstract class AbstractDeserializer<T> extends JsonDeserializer<T> {
    protected  <T> T deserialize(TreeNode node, Class<T> tClass) throws JsonProcessingException {
        return SerializationUtils.objectMapper.treeToValue(node, tClass);
    }

    protected int getInt(TreeNode node) {
        return ((IntNode) node).intValue();
    }

    protected boolean getBoolean(TreeNode node) {
        return ((BooleanNode) node).booleanValue();
    }
}
