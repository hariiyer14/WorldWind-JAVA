/*
Copyright (C) 2001, 2009 United States Government
as represented by the Administrator of the
National Aeronautics and Space Administration.
All Rights Reserved.
*/

package gov.nasa.worldwind.examples;

import gov.nasa.worldwind.WorldWind;
import gov.nasa.worldwind.avlist.AVKey;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.*;

import java.util.ArrayList;

/**
 * Shows Polygon usage. Sets material, opacity and other attributes. Sets rotation and other properties. Adds an image
 * for texturing. Shows a dateline-spanning Polygon.
 *
 * @author tag
 * @version $Id: Polygons.java 14177 2010-12-03 00:28:05Z tgaskins $
 */
public class Polygons extends ApplicationTemplate
{
    public static class AppFrame extends ApplicationTemplate.AppFrame
    {
        public AppFrame()
        {
            super(true, true, false);

//            makeMany();

            RenderableLayer layer = new RenderableLayer();

            // Create and set an attribute bundle.
            ShapeAttributes normalAttributes = new BasicShapeAttributes();
            normalAttributes.setInteriorMaterial(Material.YELLOW);
            normalAttributes.setOutlineOpacity(0.5);
            normalAttributes.setInteriorOpacity(0.8);
            normalAttributes.setOutlineMaterial(Material.GREEN);
            normalAttributes.setOutlineWidth(2);
            normalAttributes.setDrawOutline(true);
            normalAttributes.setDrawInterior(true);
            normalAttributes.setEnableLighting(true);

            ShapeAttributes highlightAttributes = new BasicShapeAttributes(normalAttributes);
            highlightAttributes.setOutlineMaterial(Material.WHITE);
            highlightAttributes.setOutlineOpacity(1);

            // Create a polygon, set some of its properties and set its attributes.
            ArrayList<Position> pathPositions = new ArrayList<Position>();
            pathPositions.add(Position.fromDegrees(28, -106, 3e4));
            pathPositions.add(Position.fromDegrees(35, -104, 3e4));
            pathPositions.add(Position.fromDegrees(35, -107, 9e4));
            pathPositions.add(Position.fromDegrees(28, -107, 9e4));
            pathPositions.add(Position.fromDegrees(28, -106, 3e4));
            Polygon pgon = new Polygon(pathPositions);
            pgon.setValue(AVKey.DISPLAY_NAME, "Has a hole\nRotated -170\u00b0");

            pathPositions.clear();
            pathPositions.add(Position.fromDegrees(29, -106.4, 4e4));
            pathPositions.add(Position.fromDegrees(30, -106.4, 4e4));
            pathPositions.add(Position.fromDegrees(29, -106.8, 7e4));
            pathPositions.add(Position.fromDegrees(29, -106.4, 4e4));
            pgon.addInnerBoundary(pathPositions);
            pgon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            pgon.setAttributes(normalAttributes);
            pgon.setHighlightAttributes(highlightAttributes);
            pgon.setRotation(-170d);
            layer.addRenderable(pgon);

            ArrayList<Position> pathLocations = new ArrayList<Position>();
            pathLocations.add(Position.fromDegrees(28, -110, 5e4));
            pathLocations.add(Position.fromDegrees(35, -108, 5e4));
            pathLocations.add(Position.fromDegrees(35, -111, 5e4));
            pathLocations.add(Position.fromDegrees(28, -111, 5e4));
            pathLocations.add(Position.fromDegrees(28, -110, 5e4));
            pgon = new Polygon(pathLocations);
            pgon.setValue(AVKey.DISPLAY_NAME, "Has an image");
            normalAttributes = new BasicShapeAttributes(normalAttributes);
            normalAttributes.setDrawInterior(true);
            normalAttributes.setInteriorMaterial(Material.WHITE);
            normalAttributes.setInteriorOpacity(1);
            pgon.setAttributes(normalAttributes);
            pgon.setHighlightAttributes(highlightAttributes);
            float[] texCoords = new float[] {0, 0, 1, 0, 1, 1, 0, 1, 0, 0};
            pgon.setTextureImageSource("images/32x32-icon-nasa.png", texCoords, 5);
            layer.addRenderable(pgon);

            pathLocations.clear();
            pathLocations.add(Position.fromDegrees(28, -170, 29e4));
            pathLocations.add(Position.fromDegrees(35, -174, 29e4));
            pathLocations.add(Position.fromDegrees(35, 174, 29e4));
            pathLocations.add(Position.fromDegrees(28, 170, 29e4));
            pathLocations.add(Position.fromDegrees(28, -170, 29e4));
            pgon = new Polygon(pathLocations);
            pgon.setValue(AVKey.DISPLAY_NAME, "Spans dateline\nRotated -45\u00b0");
            normalAttributes = new BasicShapeAttributes(normalAttributes);
            normalAttributes.setDrawInterior(true);
            pgon.setAttributes(normalAttributes);
            pgon.setHighlightAttributes(highlightAttributes);
            pgon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
            pgon.setRotation(-45d);
            layer.addRenderable(pgon);

            // Add the layer to the model.
            insertBeforeCompass(getWwd(), layer);

            // Update layer panel
            this.getLayerPanel().update(this.getWwd());
        }

        protected void makeMany()
        {
            double minLat = -50, maxLat = 50, minLon = -150, maxLon = 150;
            double delta = 2;
            double altitude = 1e4;

            RenderableLayer layer = new RenderableLayer();

            int count = 0;
            for (double lat = minLat; lat <= maxLat; lat += delta)
            {
                for (double lon = minLon; lon <= maxLon; lon += delta)
                {
                    ArrayList<Position> positions = new ArrayList<Position>();
                    positions.add(Position.fromDegrees(lat, lon, altitude));
                    positions.add(Position.fromDegrees(lat, lon + 1, altitude));
                    positions.add(Position.fromDegrees(lat + 1, lon + 1, altitude));
                    positions.add(Position.fromDegrees(lat + 1, lon, altitude));
                    positions.add(Position.fromDegrees(lat, lon, altitude));

                    Polygon pgon = new Polygon(positions);
//                    pgon.setAltitudeMode(WorldWind.RELATIVE_TO_GROUND);
                    layer.addRenderable(pgon);
                    ++count;
                }
            }
            System.out.printf("%d Polygons\n", count);

            insertBeforeCompass(getWwd(), layer);
            this.getLayerPanel().update(this.getWwd());
        }
    }

    public static void main(String[] args)
    {
        ApplicationTemplate.start("World Wind Extruded Polygons", AppFrame.class);
    }
}
