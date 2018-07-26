package yuncar.aten.com.mymode.banner;

import android.support.v4.view.ViewPager.PageTransformer;

import yuncar.aten.com.mymode.banner.transformer.AccordionTransformer;
import yuncar.aten.com.mymode.banner.transformer.BackgroundToForegroundTransformer;
import yuncar.aten.com.mymode.banner.transformer.CubeInTransformer;
import yuncar.aten.com.mymode.banner.transformer.CubeOutTransformer;
import yuncar.aten.com.mymode.banner.transformer.DefaultTransformer;
import yuncar.aten.com.mymode.banner.transformer.DepthPageTransformer;
import yuncar.aten.com.mymode.banner.transformer.FlipHorizontalTransformer;
import yuncar.aten.com.mymode.banner.transformer.FlipVerticalTransformer;
import yuncar.aten.com.mymode.banner.transformer.ForegroundToBackgroundTransformer;
import yuncar.aten.com.mymode.banner.transformer.RotateDownTransformer;
import yuncar.aten.com.mymode.banner.transformer.RotateUpTransformer;
import yuncar.aten.com.mymode.banner.transformer.ScaleInOutTransformer;
import yuncar.aten.com.mymode.banner.transformer.StackTransformer;
import yuncar.aten.com.mymode.banner.transformer.TabletTransformer;
import yuncar.aten.com.mymode.banner.transformer.ZoomInTransformer;
import yuncar.aten.com.mymode.banner.transformer.ZoomOutSlideTransformer;
import yuncar.aten.com.mymode.banner.transformer.ZoomOutTranformer;


public class Transformer {
    public static Class<? extends PageTransformer> Default = DefaultTransformer.class;
    public static Class<? extends PageTransformer> Accordion = AccordionTransformer.class;
    public static Class<? extends PageTransformer> BackgroundToForeground = BackgroundToForegroundTransformer.class;
    public static Class<? extends PageTransformer> ForegroundToBackground = ForegroundToBackgroundTransformer.class;
    public static Class<? extends PageTransformer> CubeIn = CubeInTransformer.class;
    public static Class<? extends PageTransformer> CubeOut = CubeOutTransformer.class;
    public static Class<? extends PageTransformer> DepthPage = DepthPageTransformer.class;
    public static Class<? extends PageTransformer> FlipHorizontal = FlipHorizontalTransformer.class;
    public static Class<? extends PageTransformer> FlipVertical = FlipVerticalTransformer.class;
    public static Class<? extends PageTransformer> RotateDown = RotateDownTransformer.class;
    public static Class<? extends PageTransformer> RotateUp = RotateUpTransformer.class;
    public static Class<? extends PageTransformer> ScaleInOut = ScaleInOutTransformer.class;
    public static Class<? extends PageTransformer> Stack = StackTransformer.class;
    public static Class<? extends PageTransformer> Tablet = TabletTransformer.class;
    public static Class<? extends PageTransformer> ZoomIn = ZoomInTransformer.class;
    public static Class<? extends PageTransformer> ZoomOut = ZoomOutTranformer.class;
    public static Class<? extends PageTransformer> ZoomOutSlide = ZoomOutSlideTransformer.class;
}
