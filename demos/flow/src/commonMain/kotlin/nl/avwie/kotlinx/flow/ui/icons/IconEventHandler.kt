package nl.avwie.kotlinx.flow.ui.icons

import nl.avwie.kotlinx.flow.state.IconState
import nl.avwie.kotlinx.flow.ui.common.TargetedClickEventHandler
import nl.avwie.kotlinx.flow.ui.common.TargetedDragEventHandler

interface IconEventHandler : TargetedDragEventHandler<IconState>, TargetedClickEventHandler<IconState>