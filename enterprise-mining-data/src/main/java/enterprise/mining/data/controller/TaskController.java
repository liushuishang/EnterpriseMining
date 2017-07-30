package enterprise.mining.data.controller;

import enterprise.mining.data.domain.Task;
import enterprise.mining.data.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * Created by  yuananyun on 2017/7/30.
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    @Transactional
    public Task saveTask(@RequestBody Task taskInfo) {
        Task task = taskRepository.save(taskInfo);
        return task;
    }


    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public Task create(@PathVariable String name) {
        Task task = taskRepository.findByTaskName(name);
        return task;
    }
}

