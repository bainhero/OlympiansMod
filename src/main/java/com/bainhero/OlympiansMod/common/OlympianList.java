package com.bainhero.OlympiansMod.common;

import java.util.HashMap;

import com.bainhero.OlympiansMod.common.parents.Olympian;

import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class OlympianList {

	public static final String ZEUS_KEY = "zeus";
	private static final Olympian ZEUS = new Olympian(new EffectInstance[]{});
	
	public static final String POSEIDON_KEY = "poseidon";
	private static final Olympian POSEIDON = new Olympian(new EffectInstance[]{});
	
	public static final String HADES_KEY = "hades";
	private static final Olympian HADES = new Olympian(new EffectInstance[]{});
	
	public static final String DEMETER_KEY = "demeter";
	private static final Olympian DEMETER = new Olympian(new EffectInstance[]{});
	
	public static final String ATHENA_KEY = "athena";
	private static final Olympian ATHENA = new Olympian(new EffectInstance[]{});
	
	public static final String APOLLO_KEY = "apollo";
	private static final Olympian APOLLO = new Olympian(new EffectInstance[]{});
	
	public static final String ARES_KEY = "ares";
	private static final Olympian ARES = new Olympian(new EffectInstance[]{});
	
	public static final String HEPHAESTUS_KEY = "hephaestus";
	private static final Olympian HEPHAESTUS = new Olympian(new EffectInstance[]{});
	
	public static final String APHRODITE_KEY = "aphrodite";
	private static final Olympian APHRODITE = new Olympian(new EffectInstance[]{});
	
	public static final String HERMES_KEY = "hermes";
	private static final Olympian HERMES = new Olympian(new EffectInstance[]{});
	
	public static final String DIONYSUS_KEY = "dionysus";
	private static final Olympian DIONYSUS = new Olympian(new EffectInstance[]{});
	
	private static HashMap<String, Olympian> map = new HashMap<>();
	
	public static void generateMap() {
		map.put(ZEUS_KEY, ZEUS);
		map.put(POSEIDON_KEY, POSEIDON);
		map.put(HADES_KEY, HADES);
		map.put(DEMETER_KEY, DEMETER);
		map.put(ATHENA_KEY, ATHENA);
		map.put(APOLLO_KEY, APOLLO);
		map.put(ARES_KEY, ARES);
		map.put(HEPHAESTUS_KEY, HEPHAESTUS);
		map.put(APHRODITE_KEY, APHRODITE);
		map.put(HERMES_KEY, HERMES);
		map.put(DIONYSUS_KEY, DIONYSUS);
	}
	
	public static Olympian getOlympian(String key) {
		return map.get(key);
	}
}
