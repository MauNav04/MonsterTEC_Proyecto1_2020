package JSON;

import GameObjects.PlayingCard;
import Structures.LinkedLists;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.ByteArrayOutputStream;

public class Encoder {

    public static void main(String[] args) throws JsonProcessingException {
        String cardA = encodeCard (new PlayingCard("H","Rumble",40));
        String cardB = encodeCard (new PlayingCard("A","120",30));
        String cardC = encodeCard (new PlayingCard("A","100",42));
        String cardD = encodeCard (new PlayingCard("S","TicTac",15));
        /*LinkedLists cardList = new LinkedLists();
        cardList.insert(cardA);
        cardList.insert(cardB);
        cardList.insert(cardC);
        cardList.insert(cardD);*/

        //encodeHand(cardList);
    }

    public static String encodeCard(PlayingCard card) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); //solo para printear bien
        String jsonString = mapper.writeValueAsString(card);

        //the field names of the objects become the JSON keys
        System.out.println(jsonString);
        return jsonString;
    }

    public static void encodeHand(LinkedLists hand) throws JsonProcessingException {
        int listSize  = hand.getSize();
        String jsonString = "[";

        for (int i = 0; i < listSize; i++) {
            String encodedCard = hand.get(i).toString();
            if (i != listSize - 1) {
                jsonString = jsonString + encodedCard + ",";
            }
            else jsonString = jsonString + encodedCard ;
        }

        jsonString = jsonString + "]";

        //the field names of the objects become the JSON keys
        System.out.println(jsonString);
    }

}
