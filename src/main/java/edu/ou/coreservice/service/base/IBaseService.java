package edu.ou.coreservice.service.base;

public interface IBaseService<I, O> {
    /**
     * Execute a task
     *
     * @param input input of task
     * @return output of task
     * @author Nguyen Trung Kien - OU
     */
    O execute(I input);
}
