
package com.oratakashi.widgets;

import android.graphics.Path;

interface Renderer {
    Path buildPath(float animationProgress, float animationSeek);
}
