import java.util.List;
import java.util.stream.Collectors;
import java.nio.file.Paths;
import java.util.Arrays;
import java.nio.file.Files;
import java.io.IOException;
import java.text.DecimalFormat;

public class Covid
{
  // You can add your own variables between them.

  // You can add your own variables between them.

  // You must not change between them.
  private List<List<String>> rows;

  public Covid()
  {
    try
    {
      this.rows = Files
  				.lines(Paths.get("covid19.csv"))
  				.map(row -> Arrays.asList(row.split(",")))
  				.collect(Collectors.toList());
    }
    catch (IOException e)
    {
			e.printStackTrace();
		}
  }
  // You must not change between them.

  public void printOnlyCases(String location, String date)
  {
    this.rows.stream().filter(b -> b.get(1).equals(location) && (b.get(2).equals(date))).map(n->{
       int total_cases = Integer.parseInt(n.get(3));
       int total_deaths = Integer.parseInt(n.get(5));
       int only_cases =total_cases-total_deaths;
       return only_cases;
     }).forEach( x -> System.out.printf("Result: %s\n",x.toString()));
  }

  public long getDateCount(String location)
  {
    long row_count = 0;
    row_count = this.rows.stream().filter(b -> b.get(1).equals(location)).count();
    return row_count;
  }

  public int getCaseSum(String date)
  {
    int sum = 0;
    sum = this.rows.stream().filter(b->b.get(2).equals(date) && !b.get(2).equals(null)).mapToInt(a -> Integer.parseInt(a.get(4))).sum();
    return sum;
  }

  public long getZeroRowsCount(String location)

  {
    long row_count = 0;
    row_count = this.rows.stream().filter(b->b.get(1).equals(location) && b.get(3).equals("0") && b.get(4).equals("0") && b.get(5).equals("0")
      && b.get(6).equals("0")).count();
    return row_count;
  }

  public double getAverageDeath(String location)
  {
    double average = 0;
    double sum;
    sum = this.rows.stream().filter( b -> b.get(1).equals(location)).mapToInt(a -> Integer.parseInt(a.get(6))).sum();
    double count = this.rows.stream().filter(b-> b.get(1).equals(location)).count();
    average = sum/count;
    average = Double.parseDouble(new DecimalFormat("##.00").format(average));
    return average;
  }

  public String getFirstDeathDayInFirstTenRows(String location)
  {
    String toReturn = null;

    if(this.rows.stream().filter(b-> b.get(1).equals(location)).limit(10).filter(x->Integer.parseInt(x.get(6))>0).findFirst().isEmpty()){
      toReturn="Not Found";
    }else{
      toReturn = this.rows.stream().filter(b-> b.get(1).equals(location)).limit(10).filter(x->Integer.parseInt(x.get(6))>0).findFirst().get().get(2);
    }

    return toReturn;
  }

  public String[] getDateCountOfAllLocations()
  {
    String[] toReturn = null;

    List<String> locations = this.rows.stream().map(x-> x.get(0) ).distinct().collect(Collectors.toList());
    String row_counts;
    toReturn  = new String[locations.size()];
    for (int i = 0; i < locations.size() ; i++) {
      String country_code = locations.get(i);
      row_counts = locations.get(i)+": " + this.rows.stream().filter(b -> b.get(0).equals(country_code)).count();
      toReturn[i] = row_counts;
      System.out.println(toReturn[i]);
    }

    return toReturn;
  }

  public List<String> getLocationsFirstDeathDay()
  {
    List<String> toReturn = null;

    List<List<String>> listOfFirstDeaths= this.rows.stream().filter(b->b.get(5).equals(b.get(6)) && !b.get(5).equals("0") &&
            !b.get(6).equals("0") ).collect(Collectors.toList());
    toReturn = listOfFirstDeaths.stream().map(x->x.get(1) +": "+x.get(2)).collect(Collectors.toList());
    return toReturn;
  }

  public String trimAndGetMax(String location, int trimCount)
  {
    String toReturn = null;
    List<List<String>> sortedList;

    sortedList = this.rows.stream().filter(b-> b.get(1).equals(location)).sorted((d1,d2) ->{
             int x = Integer.parseInt(d1.get(4));
             int y = Integer.parseInt(d2.get(4));
             return Integer.compare(x,y);
            }
            ).collect(Collectors.toList());

    for (int i=0;i<trimCount;i++){
      sortedList.remove(i);
    }
    for (int i=sortedList.size()-trimCount;i<sortedList.size();i++){
      sortedList.remove(i);
    }
    toReturn = sortedList.stream().max((d1,d2) ->{
      int x = Integer.parseInt(d1.get(6));
      int y = Integer.parseInt(d2.get(6));
      return Integer.compare(x,y);
    }).map(x->x.get(2)+": "+x.get(6)).get();
    System.out.println(toReturn);
    return toReturn;
  }

  public List<List<String>> getOnlyCaseUpDays(String location)
  {
    List<List<String>> toReturn = null;

    toReturn = this.rows.stream().filter(b-> !b.get(4).equals("0") && b.get(1).equals(location)).collect(Collectors.toList());
    long row_count = toReturn.stream().count();
    System.out.printf("Result: %d",row_count);
    return toReturn;
  }

  public static void main(String[] args)
  {
    Covid covid = new Covid();
    System.out.println(covid.rows.get(0));
    covid.printOnlyCases("Aruba","2020-03-13");

    covid.printOnlyCases("Turkey", "2020-03-20");
    /*
    System.out.println(covid.getDateCount("Turkey"));
    System.out.println(covid.getDateCount(("Italy")));
    System.out.println(covid.getCaseSum("2020-03-05"));
    System.out.println(covid.getCaseSum("2020-04-05"));
    System.out.println(covid.getZeroRowsCount("Turkey"));
    System.out.println(covid.getZeroRowsCount("Australia"));
    System.out.println(covid.getAverageDeath("Turkey"));
    System.out.println(covid.getAverageDeath("Italy"));
    System.out.println(covid.getFirstDeathDayInFirstTenRows("Turkey"));
    covid.getDateCountOfAllLocations();
    List<String> stringList = covid.getLocationsFirstDeathDay();
    for (int i = 0; i <stringList.size() ; i++) {

      System.out.println(stringList.get(i));

    }*/
    covid.trimAndGetMax("Turkey",5);
    covid.getOnlyCaseUpDays("Aruba");
    // You can test your function in here.
    // This part is not graded.
  }
}
