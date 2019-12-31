/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ly.oa.common.util;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


/**
 * 响应信息主体
 *
 * @author ly
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class APIResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private int code;
	private String message;
	private Object data;

	public static APIResponse ok(Object data) {
		return APIResponse.builder()
				.code(HttpStatus.OK.value())
				.message("success")
				.data(data).build();
	}
}

