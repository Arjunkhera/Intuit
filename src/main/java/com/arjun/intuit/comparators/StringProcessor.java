package com.arjun.intuit.comparators;

public class StringProcessor implements Processor<String> {

  double threshold;

  StringProcessor(double threshold) {
    this.threshold = threshold;
  }

  @Override
  public double process(String first, String second) {
    double distance = findLevenshteinDistance(first, second);
    distance = Math.min(distance, threshold);

    return (threshold - distance) / threshold;
  }

  private int findLevenshteinDistance(String word1, String word2) {
    int[][] ans = new int[word1.length() + 1][word2.length() + 1];
    for (int i = 0; i <= word1.length(); i++) {
      ans[i][0] = i;
    }

    for (int i = 0; i <= word2.length(); i++) {
      ans[0][i] = i;
    }

    for (int i = 1; i <= word1.length(); i++) {
      for (int j = 1; j <= word2.length(); j++) {
        int min = Math.min(Math.min(ans[i][j - 1], ans[i - 1][j]), ans[i - 1][j - 1]);
        ans[i][j] = word1.charAt(i - 1) == word2.charAt(j - 1) ? ans[i - 1][j - 1] : min + 1;
      }
    }
    return ans[word1.length()][word2.length()];
  }
}
