package net.seabears.stats.football;

import java.io.IOException;

import net.seabears.stats.football.App;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import com.almworks.sqlite4java.SQLiteException;

public class FootballTest
{
  final static String BASE_URL_LEAGUE = System.getProperty("net.seabears.stats.football.url");
  final static String KEY_HREF = "href";

  @Ignore
  @Test
  public void testFootball() throws IOException
  {
    // load the scores page
    Document doc = Jsoup.connect(BASE_URL_LEAGUE + "/scores").get();

    // get week links
    Elements weekLinks = doc.select("ul.reg-season-games li a");
    for (Element link : weekLinks)
    {
      System.err.println(getFootballAbsoluteUrl(link.attr(KEY_HREF)));
    }

    // get week links
    Elements gameLinks = doc.select("a.game-center-link");
    for (Element link : gameLinks)
    {
      System.err.println(getFootballAbsoluteUrl(link.attr(KEY_HREF)));
    }

    if (!gameLinks.isEmpty())
    {
      Document gameDoc = Jsoup.connect(getFootballAbsoluteUrl(gameLinks.get(0).attr(KEY_HREF)) + "#menu=gameinfo&tab=analyze").get();

      Elements finalSpan = gameDoc.select("div#dcmm span.msg");
      System.err.println(finalSpan.size());
      System.err.println(finalSpan.get(0).outerHtml());
      System.err.println(finalSpan.get(0).text());

      Elements table = gameDoc.select("table.gc-box-score-table");
      System.out.println(table.size());
    }
  }

  public static String getFootballAbsoluteUrl(String url)
  {
    if (StringUtils.isEmpty(url) || url.charAt(0) == '/')
    {
      return BASE_URL_LEAGUE + url;
    }

    return url;
  }

  @Ignore
  @Test
  public void test() throws SQLiteException, IOException
  {
    App.main(null);
  }
}
