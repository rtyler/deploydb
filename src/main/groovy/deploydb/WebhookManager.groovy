package deploydb

import io.dropwizard.lifecycle.Managed

import com.github.lookout.whoas.InMemoryQueue
import com.github.lookout.whoas.SequentialHookRunner
import com.github.lookout.whoas.AbstractHookRunner
import com.github.lookout.whoas.AbstractHookQueue

class WebhookManager implements Managed {
    private Thread runnerThread
    private SequentialHookRunner runner
    private InMemoryQueue queue = new InMemoryQueue()

    WebhookManager() {
        runner = new SequentialHookRunner(this.queue)
        runnerThread = new Thread({
            runner.run()
        })
        runnerThread.name = 'WebhookManager Thread'
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

    AbstractHookRunner getRunner() {
        return this.runner
    }

    AbstractHookQueue getQueue() {
        return this.queue
    }
}
