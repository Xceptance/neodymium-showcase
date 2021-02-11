package showcase.neodymium.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.xceptance.neodymium.util.DataUtils;

public abstract class NeoDataUtils extends JsonElement {

  public NeoDataUtils() {
    super();
  }

  /**
   * 2020-06-30 Robby Klehm: handle nested array data sets within Neodymium
   * 
   * TEMPLATE to iterate through a double nested array dataset.
   * 
   * <pre>
   * NeoDataUtils.getDataArray("jobs").forEach(job -> {
   *   String jobName = NeoDataUtils.getDataArrayElement(job, "job");
   * 
   *   NeoDataUtils.getDataArray(job, "parameters").forEach(parameter -> {
   *     String pName = NeoDataUtils.getDataArrayElement(parameter, "parameter");
   * 
   *   });
   * });
   * </pre>
   * 
   * The dataset looks like this:
   * 
   * <pre>
   * [
   *   {
   *     "jobs": [
   *       {
   *         "job": "Replication Process Scheduler",
   *         "parameters": [
   *           {
   *             "parameter": "Enabled",
   *             "value": "true"
   *           },
   *           {
   *             "parameter": "Application",
   *             "value": "None"
   *           }
   *         ]
   *       }
   *     ] 
   *   }
   * ]
   * </pre>
   */

  public static JsonArray getDataArray(JsonElement je, String elementArray) {
    if (je == null)
      return DataUtils.getDataAsJsonObject().getAsJsonArray(elementArray);
    else
      try {
        return je.getAsJsonObject().get(elementArray).getAsJsonArray();
      } catch (Exception e) {
        return new JsonArray();
      }
  }

  public static JsonArray getDataArray(String elementArray) {
    return getDataArray(null, elementArray);
  }

  public static String getDataArrayElement(JsonElement arrayElement, String element) {
    try {
      return arrayElement.getAsJsonObject().get(element).getAsString();
    } catch (Exception e) {
      return "null";
    }
  }

  public String getDataArrayElement(String element) {
    try {
      return getDataArrayElement(this, element);
    } catch (Exception e) {
      return "null";
    }
  }

}
