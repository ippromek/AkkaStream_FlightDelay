/**
  * Created by oleg.baydakov on 26/04/2017.
  */
import java.nio.file.Paths

import com.intel.analytics.bigdl.DataSet
import com.intel.analytics.bigdl.dataset._
import com.intel.analytics.bigdl.dataset.image._
import com.intel.analytics.bigdl.nn._
//import org.apache.spark.SparkContext
import com.sun.xml.internal.ws.api.server.Module

object LeNet5 {
  def apply(classNum: Int): Module[Float] = {
    val model = Sequential()
    model.add(Reshape(Array(1, 28, 28)))
      .add(SpatialConvolution(1, 6, 5, 5).setName("conv1_5x5"))
      .add(Tanh())
      .add(SpatialMaxPooling(2, 2, 2, 2))
      .add(Tanh())
      .add(SpatialConvolution(6, 12, 5, 5).setName("conv2_5x5"))
      .add(SpatialMaxPooling(2, 2, 2, 2))
      .add(Reshape(Array(12 * 4 * 4)))
      .add(Linear(12 * 4 * 4, 100).setName("fc1"))
      .add(Tanh())
      .add(Linear(100, classNum).setName("fc2"))
      .add(LogSoftMax())
  }
}
