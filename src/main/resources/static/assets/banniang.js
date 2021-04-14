/* 可直接修改部分参数 */
live2d_settings['modelId'] = 6; // 默认模型 ID
live2d_settings['modelTexturesId'] = 20; // 默认材质 ID
live2d_settings['modelStorage'] = true; // 记录 ID (刷新后恢复)，可选 true(真), false(假)

live2d_settings['waifuDraggable'] = 'unlimited'; // 拖拽样式，例如 'disable'(禁用),
													// 'axis-x'(只能水平拖拽),
													// 'unlimited'(自由拖拽)
live2d_settings['waifuDraggableRevert'] = false; // 松开鼠标还原拖拽位置，可选 true(真),
													// false(假)

live2d_settings['canTurnToHomePage'] = false; // 隐藏 返回首页 按钮
live2d_settings['waifuSize'] = '300x300'; // 看板娘大小
live2d_settings['waifuTipsSize'] = '250x70'; // 提示框大小
live2d_settings['waifuEdgeSide'] = 'right:30';
live2d_settings['canTurnToAboutPage'] = false; // 显示 跳转关于页 按钮，可选 true(真),
												// false(假)

// live2d_settings['modelAPI'] = '/live2d_api/'; // 自建 API 修改这里
live2d_settings['hitokotoAPI'] = 'hitokoto.cn'; // 一言 API，可选 'lwl12.com',
												// 'hitokoto.cn',
												// 'jinrishici.com'(古诗词)

/* 在 initModel 前添加 */
initModel("/assets/waifu-tips.json")
var jq = jQuery.noConflict();
