/*
Copyright (C) 2001, 2006 United States Government
as represented by the Administrator of the
National Aeronautics and Space Administration.
All Rights Reserved.
*/
package gov.nasa.worldwind.layers.Earth;

import gov.nasa.worldwind.layers.BasicTiledImageLayer;
import gov.nasa.worldwind.util.WWXML;
import org.w3c.dom.Document;

/**
 * @author Tom Gaskins
 * @version $Id: BMNGSurfaceLayer.java 11316 2009-05-26 23:06:47Z dcollins $
 * @deprecated Replaced by {@link BMNGWMSLayer}.
 */
public class BMNGSurfaceLayer extends BasicTiledImageLayer
{
    public BMNGSurfaceLayer()
    {
        super(getConfigurationDocument(), null);
    }

    protected static Document getConfigurationDocument()
    {
        return WWXML.openDocumentFile("config/Earth/LegacyBMNGSurfaceLayer.xml", null);
    }
}
