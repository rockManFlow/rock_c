package com.kuark.tool.advance.advance20201111.design.state;

public interface State {
    // 默认实现，不做任何处理
    default void update(MainState.Task task, ActionType actionType) {
        // do nothing
    }
}
