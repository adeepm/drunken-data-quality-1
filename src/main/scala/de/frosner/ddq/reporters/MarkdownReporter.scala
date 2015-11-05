package de.frosner.ddq.reporters

import java.io.PrintStream

import de.frosner.ddq._
import de.frosner.ddq.core.{ConstraintSuccess, ConstraintFailure, CheckResult}

/**
 * A class which produces a markdown report of [[CheckResult]].
 *
 * @param stream The [[java.io.PrintStream]] to put the output. The stream will not be closed internally and can
 *               be reused.
 **/
case class MarkdownReporter(stream: PrintStream) extends PrintStreamReporter {

  /**
   * Output markdown report of a given checkResult to the stream passed to the constructor
   * @param checkResult The [[CheckResult]] to be reported
   */
  override def report(checkResult: CheckResult): Unit = {
    stream.println(s"**${checkResult.header}**\n")
    stream.println(s"${checkResult.prologue}\n")
    if (checkResult.constraintResults.nonEmpty)
      checkResult.constraintResults.foreach {
        case (_, ConstraintSuccess(message)) => stream.println("- *SUCCESS*: " + message)
        case (_, ConstraintFailure(message)) => stream.println("- *FAILURE*: " + message)
      }
    else
      stream.println("Nothing to check!")
    stream.println("")
  }
}