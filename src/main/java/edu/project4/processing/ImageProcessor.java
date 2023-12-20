package edu.project4.processing;

import edu.project4.domain.FractalImage;

@FunctionalInterface
public interface ImageProcessor {
    void process(FractalImage image);
}
