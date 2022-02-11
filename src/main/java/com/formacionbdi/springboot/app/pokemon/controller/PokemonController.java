package com.formacionbdi.springboot.app.pokemon.controller;


import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.MediaType;



@RestController
@RequestMapping("/pokemon")
@CrossOrigin
public class PokemonController {

	@Autowired
	private RestTemplate restTemplate;
	
	private static String urlApiPokemon = "https://pokeapi.co/api/v2/";
	
	@GetMapping("/name/{name}")
	public String getPokemonSearch(@PathVariable("name") String name) {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters",header);
		
		ResponseEntity<String> response = restTemplate.exchange(urlApiPokemon+"pokemon/"+name, HttpMethod.GET, entity, String.class);
		
		JSONObject json = new JSONObject(response.getBody());
		String namePokemon = json.getString("name");
		
	    return namePokemon.toString();
	}
	
	@GetMapping("/id/{id}")
	public Object getPokemonById(@PathVariable("id") String id){
		int idPokemon = Integer.parseInt(id);
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters",header);
		
		ResponseEntity<String> response = restTemplate.exchange(urlApiPokemon+"pokemon/"+idPokemon, HttpMethod.GET, entity, String.class);
		
		JSONObject json = new JSONObject(response.getBody());
		String namePokemon = json.getString("name");
		
	    return namePokemon.toString();
	}
	
	@GetMapping("/abilities/{id}")
	public Object getPokemonAbility(@PathVariable("id") String id){
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters",header);
		
		int idPokemon = Integer.parseInt(id);

		ResponseEntity<Object> response = restTemplate.exchange(urlApiPokemon+"ability/"+idPokemon, HttpMethod.GET, entity, Object.class);
		
		return response;
	}
	
	@GetMapping("/held-items/{name}")
	public String getPokemonHeldItems(@PathVariable("name") String name){
		
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters",header);
		
		ResponseEntity<String> response = restTemplate.exchange(urlApiPokemon+"pokemon/"+name, HttpMethod.GET, entity, String.class);
		
		JSONObject json = new JSONObject(response.getBody());
		int tam = json.getJSONArray("held_items").toList().size();
		ArrayList<String> listItems = new ArrayList<String>();
		for (int i = 0; i < tam; i++) {
			listItems.add(json.getJSONArray("held_items").getJSONObject(i).getJSONObject("item").getString("name"));
		}
		
 	    return listItems.toString();
	}
	
	
	@GetMapping("/location-area-encounters/{name}")
	public Object getPokemonocationAreaEncounters(@PathVariable("name") String name){
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		header.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
		HttpEntity<String> entity = new HttpEntity<String>("parameters",header);
		
		ResponseEntity<Object> response = restTemplate.exchange(urlApiPokemon+"pokemon/"+name+"/encounters", HttpMethod.GET, entity, Object.class);
		
 	    return response;
	}
	
	
	
}
