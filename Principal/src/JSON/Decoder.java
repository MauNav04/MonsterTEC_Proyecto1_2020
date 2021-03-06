package JSON;

import GameObjects.Deck;
import GameObjects.PlayingCard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;

public class Decoder {

    public static void main(String[] args) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Deck completeDeck = mapper.readValue(Decoder.class.getResourceAsStream("/GameObjects/Cards.json"), Deck.class);
        //Deck completeDeck = mapper.readValue(Paths.get("/home/mauro/Documents/GitKrakenClones/MonsterTEC_Proyecto1_2020/Sockets/src/GameObjects/Cards.json").toFile(), Deck.class);

        PlayingCard[] cards = completeDeck.getCompleteDeck();

        PlayingCard card = cards[2];

        System.out.println(card.attribute);
    }

    public Message Decode(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(json, Message.class);
        return message;
    }

    public PlayingCard[] DecodeCardFile() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        Deck completeDeck = mapper.readValue(Decoder.class.getResourceAsStream("/GameObjects/Cards.json"), Deck.class);

        PlayingCard[] cards = completeDeck.getCompleteDeck();

        PlayingCard card = cards[2];

        System.out.println(card.attribute);

        return completeDeck.getCompleteDeck();
    }


}
