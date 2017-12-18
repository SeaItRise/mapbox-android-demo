package com.sea_it_rise.mapboxandroiddemo.examples.plugins;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.mapbox.androidsdk.plugins.building.BuildingPlugin;
import com.sea_it_rise.mapboxandroiddemo.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

/**
 * Use the buildings plugin to display buildings' heights (extrusions) in 3D.
 */
public class BuildingPluginActivity extends AppCompatActivity {

  private MapView mapView;
  private BuildingPlugin buildingPlugin;
  private MapboxMap map;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // Mapbox access token is configured here. This needs to be called either in your application
    // object or in the same activity which contains the mapview.
    Mapbox.getInstance(this, getString(R.string.access_token));

    setContentView(R.layout.activity_building_plugin);

    //mapView = (MapView) findViewById(R.id.mapView);
      MapboxMapOptions options = new MapboxMapOptions()
              .styleUrl(Style.OUTDOORS)
              .camera(new CameraPosition.Builder()
                      .target(new LatLng(47.60054, -122.3315))
                      .zoom(12)
                      .build());


    mapView.onCreate(savedInstanceState);
    mapView.getMapAsync(new OnMapReadyCallback() {
      @Override
      public void onMapReady(@NonNull final MapboxMap map) {

/*          map = MapboxMap;
          RasterSource webMapSource = new RasterSource(
                  "noaa-3ft-source",
                            *//*new TileSet("raster", "https://geodata.state.nj.us/imagerywms/Natural2015?bbox={"
                                            + "bbox-epsg-3857}&format=image/png&service=WMS&version=1.1.1&request=GetMap&"
                                            + "srs=EPSG:3857&width=256&height=256&layers=Natural2015"), 256);
*//*
                  new TileSet("raster","https://www.coast.noaa.gov/arcgis/rest/services/dc_slr/"
                          + "conf_3ft/MapServer/tile/{z}/{y}/{x}"), 256);

          map.addSource(webMapSource);

          // Add the web map source to the map.
          RasterLayer webMapLayer = new RasterLayer("noaa-3ft-layer", "noaa-3ft-source");
          map.addLayerBelow(webMapLayer, "aeroway-taxiway");*/
          //noaa3ftlayer = map.getLayer("noaa-3ft-layer");
          //noaa3ftlayer.setProperties();
          buildingPlugin = new BuildingPlugin(mapView, map);
        buildingPlugin.setVisibility(true);
      }
    });
  }

  @Override
  protected void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override
  protected void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onStop() {
    super.onStop();
    mapView.onStop();
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
}