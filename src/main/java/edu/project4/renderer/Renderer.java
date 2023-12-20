package edu.project4.renderer;

import edu.project4.domain.FractalImage;
import edu.project4.domain.Rect;
import edu.project4.transformation.Transformation;
import java.util.List;

public interface Renderer {
    FractalImage render(
        FractalImage canvas,
        Rect world,
        List<Transformation> transformations,
        int samples,
        short iterPerSample,
        long seed
    );
}
