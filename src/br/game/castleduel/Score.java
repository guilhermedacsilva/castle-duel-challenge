package br.game.castleduel;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Score {
	private Map<String, Integer> points = new HashMap<>();
	
	public void init(Collection<String> names) {
		for (String name : names) {
			if (!points.containsKey(name)) {
				points.put(name, 0);
			}
		}
	}

	public void increment(String playerWonRealName) {
		Integer point = points.get(playerWonRealName);
		points.put(playerWonRealName, point + 1);
	}

	public void print() {
		for(Entry<String, Integer> entry : points.entrySet()) {
			System.out.println(entry.getKey() + ": " + entry.getValue());
		}
	}

	public void saveToFile() {
		try {
			ArrayList<PlayerScore> scoreList = createSortedList();
			File fileScore = new File("score.txt");
			if (!fileScore.exists()) {
				fileScore.createNewFile();
			}
			StringBuilder builder = new StringBuilder();
			for (PlayerScore player: scoreList) {
				builder.append(player.name);
				builder.append("\n");
			}
			builder.deleteCharAt(builder.length()-1);
			Files.write(fileScore.toPath(), builder.toString().getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private ArrayList<PlayerScore> createSortedList() {
		ArrayList<PlayerScore> scores = new ArrayList<>();
		for (Entry<String, Integer> entry : points.entrySet()) {
			scores.add(new PlayerScore(entry));
		}
		scores.sort(new Comparator<Score.PlayerScore>() {
			public int compare(Score.PlayerScore o1, Score.PlayerScore o2) {
				return (int) Math.signum(o2.score - o1.score);
			};
		});
		return scores;
	}
	
	private class PlayerScore {
		private final String name;
		private final int score;
		
		public PlayerScore(Entry<String, Integer> entry) {
			name = entry.getKey();
			score = entry.getValue();
		}
	}
}
