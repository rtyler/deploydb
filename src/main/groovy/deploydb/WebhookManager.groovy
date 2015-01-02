package deploydb

import io.dropwizard.lifecycle.Managed

import com.github.lookout.whoas.InMemoryQueue
import com.github.lookout.whoas.SequentialHookRunner

class WebhookManager implements Managed {
    private Thread runnerThread
    private SequentialHookRunner runner
    private InMemoryQueue queue = new InMemoryQueue()

    WebhookManager() {
        runner = new SequentialHookRunner(this.queue)
        runnerThread = new Thread({
            runner.run()
        })
    }

    @Override
    void start() {
        runnerThread.start()
    }

    @Override
    void stop() {
        runner.stop()
    }

    /**
     * Return true if the webhook thread is running
     */
    Boolean getRunning() {
        return runnerThread.isAlive()
    }
}
