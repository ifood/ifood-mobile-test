//
//  ResponseData.swift
//  IfoodMobileTest
//
//  Created by André Vieira on 09/06/19.
//  Copyright © 2019 André Vieira. All rights reserved.
//

import Foundation

extension JSONDecoder {
    func decode<T>(_ type: T.Type, from json: NSDictionary) throws -> T where T: Decodable {
        do {
            let data = try JSONSerialization.data(withJSONObject: json, options: .prettyPrinted)
            return try self.decode(type, from: data)
        } catch let error {
            throw error
        }
    }
}

public extension Data {
    
    func mapToObject<Model: Codable>() throws -> Model {
        do {
            let json = try self.mapToJSON()
            return try self.mapModel(json: json)
        } catch let error {
            throw error
        }
    }
    
    func mapToArray<Model: Codable>() throws -> [Model] {
        do {
            let json = try self.mapToJSON()
            guard let array = json as? NSArray else {
                throw JSONParseError.convertibleError(value: json, type: [Model].Type.self)
            }
            return try Array(array).map { try self.mapModel(json: $0) }
        } catch let error {
            throw error
        }
    }
    
    private func mapModel<Model: Codable>(json: Any) throws -> Model {
        guard let dict = json as? NSDictionary else {
            throw JSONParseError.convertibleError(value: json, type: Model.Type.self)
        }
        do {
            let parsedJson = try JSONDecoder().decode(Model.self, from: dict)
            return parsedJson
        } catch {
            throw JSONParseError.invalid(json)
        }
    }
    
    func mapToJSON() throws -> Any {
        do {
            return try JSONSerialization.jsonObject(with: self, options: [])
        } catch {
            throw DataError.underlying(error)
        }
    }
}
