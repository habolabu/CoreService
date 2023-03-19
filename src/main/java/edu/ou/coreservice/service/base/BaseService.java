package edu.ou.coreservice.service.base;

public abstract class BaseService<I, O> implements IBaseService<I, O> {

    /**
     * Execute a task
     *
     * @param input input of task
     * @return output of task
     * @author Nguyen Trung Kien - OU
     */
    @Override
    public O execute(I input) {
        try {
            preExecute(input);
            return doExecute(input);
        } finally {
            postExecute(input);
        }
    }

    /**
     * Handle pre-execution
     *
     * @param input input of task
     * @author Nguyen Trung Kien - OU
     */
    protected abstract void preExecute(I input);

    /**
     * Process business
     *
     * @param input input of task
     * @return output of task
     * @author Nguyen Trung Kien - OU
     */
    protected abstract O doExecute(I input);

    /**
     * Handle post-execution
     *
     * @param input input of task
     * @author Nguyen Trung Kien - OU
     */
    protected abstract void postExecute(I input);
}
