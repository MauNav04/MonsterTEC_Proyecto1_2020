package JSON;

import GameObjects.PlayingCard;
import Structures.LinkedLists;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Decoder {

    public static void main(String[] args) throws JsonProcessingException {

        String json = "{\"type\":\"S\",\"attribute\":\"TicTac\",\"mana\":15}";
        ObjectMapper mapper = new ObjectMapper();
        PlayingCard card = mapper.readValue();

        System.out.println(card.attribute);
        System.out.println(card.mana);


    }
}
