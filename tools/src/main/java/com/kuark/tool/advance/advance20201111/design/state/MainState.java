package com.kuark.tool.advance.advance20201111.design.state;

import lombok.Data;

/**
 * 状态模式-设计模式
 */
public class MainState {
    public static void main(String[] args) {
        /**
         * 状态模式 核心：把各个任务状态需要做的动作转到各个状态对象中来实现
         *
         */
    }



    // 任务初始状态
    class TaskInit implements State {
        //todo 初始状态逻辑：把任务中的状态变更为进行中状态
        @Override
        public void update(Task task, ActionType actionType) {
            if  (actionType == ActionType.START) {
                task.setState(new TaskOngoing());
            }
        }
    }

    // 任务进行状态
    class TaskOngoing implements State {
        //todo 进行状态逻辑：把任务中的状态变更为完成状态或者终止等状态
//        private ActivityService activityService;
//        private TaskManager taskManager;

        @Override
        public void update(Task task, ActionType actionType) {
            if (actionType == ActionType.ACHIEVE) {
                task.setState(new TaskFinished());
                // 通知
//                activityService.notifyFinished(taskId);
//                taskManager.release(taskId);
            } else if (actionType == ActionType.STOP) {
                task.setState(new TaskPaused());
            } else if (actionType == ActionType.EXPIRE) {
                task.setState(new TaskExpired());
            }
        }
    }

    // 任务暂停状态
    class TaskPaused implements State {
        @Override
        public void update(Task task, ActionType actionType) {
            if (actionType == ActionType.START) {
                task.setState(new TaskOngoing());
            } else if (actionType == ActionType.EXPIRE) {
                task.setState(new TaskExpired());
            }
        }
    }


    // 任务完成状态
    class TaskFinished implements State {

    }
    // 任务过期状态
    class TaskExpired implements State {

    }

    @Data
    class Task {
        private Long taskId;
        // 初始化为初始态
        private State state = new MainState.TaskInit();
        // 更新状态
        public void updateState(ActionType actionType) {
            state.update(this, actionType);
        }
    }
}
