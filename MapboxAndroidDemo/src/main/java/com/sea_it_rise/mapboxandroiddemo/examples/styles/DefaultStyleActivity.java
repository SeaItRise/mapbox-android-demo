package com.sea_it_rise.mapboxandroiddemo.examples.styles;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.sea_it_rise.mapboxandroiddemo.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.style.layers.RasterLayer;
import com.mapbox.mapboxsdk.style.sources.RasterSource;
import com.mapbox.mapboxsdk.style.sources.TileSet;

/**
 * Use a variety of professionally designed styles with the Mapbox Android SDK.
 */
public class DefaultStyleActivity extends AppCompatActivity {

  private MapView mapView;
  private MapboxMap mapboxMap;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Mapbox access token is configured here. This needs to be called either in your application
    // object or in the same activity which contains the mapview.
    Mapbox.getInstance(this, getString(R.string.access_token));

    // This contains the MapView in XML and needs to be called after the access token is configured.
    setContentView(R.layout.activity_style_default);

    mapView = (MapView) findViewById(R.id.mapView);
    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(MapboxMap mapboxMap) {
        DefaultStyleActivity.this.mapboxMap = mapboxMap;
        mapboxMap = mapboxMap;
        RasterSource webMapSource = new RasterSource(
                "noaa-3ft-source",
                            /*new TileSet("raster", "https://geodata.state.nj.us/imagerywms/Natural2015?bbox={"
                                            + "bbox-epsg-3857}&format=image/png&service=WMS&version=1.1.1&request=GetMap&"
                                            + "srs=EPSG:3857&width=256&height=256&layers=Natural2015"), 256);
*/
                new TileSet("raster","https://www.coast.noaa.gov/arcgis/rest/services/dc_slr/"
                        + "conf_3ft/MapServer/tile/{z}/{y}/{x}"), 256);

        mapboxMap.addSource(webMapSource);

        // Add the web map source to the map.
        RasterLayer webMapLayer = new RasterLayer("noaa-3ft-layer", "noaa-3ft-source");
        mapboxMap.addLayerBelow(webMapLayer, "aeroway-taxiway");// customize map with markers, polylines, etc

      }
    });
  }

  @Override
  public void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override
  public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_map_style, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle item selection
    //RasterLayer webMapLayer = new RasterLayer("noaa-3ft-layer", "noaa-3ft-source");
    switch (item.getItemId()) {
      case R.id.menu_streets:
        mapboxMap.setStyleUrl(Style.MAPBOX_STREETS);
        //mapboxMap.addLayerBelow(webMapLayer, "aeroway-taxiway");
        return true;
      case R.id.menu_dark:
        mapboxMap.setStyleUrl(Style.DARK);
        return true;
      case R.id.menu_light:
        mapboxMap.setStyleUrl(Style.LIGHT);
        return true;
      case R.id.menu_outdoors:
        mapboxMap.setStyleUrl(Style.OUTDOORS);
        return true;
      case R.id.menu_satellite:
        mapboxMap.setStyleUrl(Style.SATELLITE);
        return true;
      case R.id.menu_satellite_streets:
        mapboxMap.setStyleUrl(Style.SATELLITE_STREETS);
        return true;
      case android.R.id.home:
        finish();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }


  }
}