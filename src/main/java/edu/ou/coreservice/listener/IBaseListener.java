package edu.ou.coreservice.listener;

public interface IBaseListener<I, O>  {
    /**
     * Execute a task
     *
     * @param input input of task
     * @return output of task
     * @author Nguyen Trung Kien - OU
     */
    O execute(I input);
}
